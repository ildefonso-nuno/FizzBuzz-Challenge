resource "google_cloud_run_service" "beluga_server" {
  name     = "${var.service_name}-server"
  location = "europe-west3"

  metadata {
    annotations = {
      "run.googleapis.com/launch-stage"  = "BETA"
    }
  }

  template {
    metadata {
      labels = {
        "run.googleapis.com/startupProbeType" = "Custom"
      }
      annotations = {
        "autoscaling.knative.dev/minScale" = "0"
        "autoscaling.knative.dev/maxScale" = "5"
        "run.googleapis.com/startup-cpu-boost" = "true"
        "run.googleapis.com/network-interfaces" = "[{\"network\":\"${var.service_name}-vpc\",\"subnetwork\":\"${var.service_name}-subnet\",\"tags\":[\"${var.service_name}-vpc\"]}]"
        "run.googleapis.com/vpc-access-egress" = "all-traffic"
      }
    }
    spec {
      containers {
        image = var.image
        startup_probe {
          initial_delay_seconds = 150
          timeout_seconds = 3
          period_seconds = 5
          failure_threshold = 1
          tcp_socket {
            port = 8080
          }
        }
        liveness_probe {
          initial_delay_seconds = 300
          timeout_seconds = 3
          period_seconds = 5
          failure_threshold = 1
          http_get {
            path = "/beluga/"
          }
        }
        resources {
          limits = {
            cpu    = "1000m"
            memory = "512Mi"
          }
        }
        ports {
          name = "http1"
          container_port = 8080
        }
      }
      service_account_name = var.service_account_email
      container_concurrency = 80
      timeout_seconds = 300
    }
  }
}

resource "google_cloud_run_service_iam_binding" "default" {
  location = google_cloud_run_service.beluga_server.location
  service  = google_cloud_run_service.beluga_server.name
  role     = "roles/run.invoker"
  members = [
    "allUsers"
  ]
}

# Output the URL of the deployed service
output "service_url" {
  value = google_cloud_run_service.beluga_server.status[0].url
}