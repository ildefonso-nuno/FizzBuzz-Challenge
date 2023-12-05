# VPC
resource "google_compute_network" "beluga-vpc" {
  name                    = "${var.service_name}-vpc"
  auto_create_subnetworks = "false"
}

# Subnet
resource "google_compute_subnetwork" "beluga-subnet" {
  name          = "${var.service_name}-subnet"
  region        = var.region
  network       = google_compute_network.beluga-vpc.name
  ip_cidr_range = "10.10.0.0/24"
}