variable "image" {
  type = string
  default = "eu.gcr.io/beluga-project-406711/beluga-service:latest"
}

variable "project_id" {
  type = string
  default = "beluga-project-406711"
}

variable "service_name" {
  type = string
  default = "beluga-service"
}

variable "service_account_email" {
  type = string
  default = "beluga-service-account@beluga-project-406711.iam.gserviceaccount.com"
}


variable "region" {
  type = string
  default = "europe-west3"
}

variable "zone" {
  type = string
  default = "europe-west3-a"
}