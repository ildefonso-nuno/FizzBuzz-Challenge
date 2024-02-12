resource "google_project_iam_binding" "role-admin" {
project = var.project_id
role    = "roles/iam.roleAdmin"

members = [
"serviceAccount:${var.service_account_email}",
]
}

resource "google_project_iam_binding" "service-account-user" {
  project = var.project_id
  role    = "roles/iam.serviceAccountUser"

  members = [
    "serviceAccount:${var.service_account_email}",
  ]
  depends_on = [google_project_iam_binding.role-admin]
}


resource "google_project_iam_custom_role" "beluga-role" {
  role_id     = "belugaRole"
  title       = "Beluga Service Role"
  description = "Custom role for the Beluga Service"
  permissions = [
    "compute.firewalls.create",
    "compute.firewalls.get",
    "compute.firewalls.delete",
    "compute.networks.create",
    "compute.networks.get",
    "compute.networks.delete",
    "compute.networks.updatePolicy",
    "compute.subnetworks.create",
    "compute.subnetworks.get",
    "compute.subnetworks.delete",
    "run.services.create",
    "run.services.get",
    "run.services.update",
    "run.services.delete",
    "run.services.setIamPolicy",
    "run.services.getIamPolicy",
    "artifactregistry.repositories.uploadArtifacts"
  ]
  depends_on = [google_project_iam_binding.service-account-user]
}

resource "google_project_iam_binding" "beluga-role-binding" {
  project = var.project_id
  role    = google_project_iam_custom_role.beluga-role.id

  members = [
    "serviceAccount:${var.service_account_email}",
  ]
  depends_on = [google_project_iam_custom_role.beluga-role]
}

/*resource "google_project_iam_binding" "service_usage_viewer" {
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
}*/

