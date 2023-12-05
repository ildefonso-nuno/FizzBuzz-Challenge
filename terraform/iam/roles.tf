resource "google_project_iam_binding" "service_usage_viewer" {
  project = var.project_id
  role    = "roles/serviceusage.serviceUsageViewer"

  members = [
    "serviceAccount:${var.service_account_email}",
  ]
}
resource "google_project_iam_member" "network_admin" {
  project = var.project_id
  role    = "roles/compute.networkAdmin"
  member  = "serviceAccount:${var.service_account_email}"
}

resource "google_project_iam_member" "service_account_pubsub_subscriber" {
  project = var.project_id
  role    = "roles/pubsub.subscriber"
  member  = "serviceAccount:${var.service_account_email}"

  depends_on = [google_project_iam_binding.service_usage_viewer]
}

