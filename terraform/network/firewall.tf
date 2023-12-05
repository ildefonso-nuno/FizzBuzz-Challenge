resource "google_compute_firewall" "allow-https" {
  name = "allow-https"
  network = google_compute_network.beluga-vpc.id
  source_ranges = ["0.0.0.0/0"]

  allow {
    protocol = "tcp"
    ports = ["443"]
  }
}