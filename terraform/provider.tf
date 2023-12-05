variable "credentials-path" {default = "./credentials"}

terraform {
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "5.7.0"
    }
  }
}

provider "google" {
  project = var.project_id
  region = var.region
  zone = var.zone
  credentials = file("${var.credentials-path}/secrets.json")
}