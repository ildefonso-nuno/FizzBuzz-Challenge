module "iam" {
  source = "./iam"
  project_id = var.project_id
  service_account_email = var.service_account_email
}

module "api" {
  source = "./api"
  image = var.image
  depends_on = [module.iam]
}

module "network" {
  source = "./network"
  service_name = var.service_name
  region = var.region
  depends_on = [module.iam]
}

module "services" {
  source = "./services"
  service_name = var.service_name
  service_account_email = var.service_account_email
  image = var.image
  project_id = var.project_id
  depends_on = [module.api, module.network]
}