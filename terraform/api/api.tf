resource "null_resource" "gradle-build" {

  provisioner "local-exec" {
    working_dir = "${path.module}/../.."
    command = <<EOT
    ls -a
    ./gradlew build
   EOT
  }
  depends_on = [google_project_service.containerregistry]

  // uncomment the following trigger to deploy image
  //triggers = {
  //  always_run = timestamp()
  //}
}

resource "null_resource" "docker-registry" {

  provisioner "local-exec" {
    working_dir = "${path.module}/../.."
    command = <<EOT
    ls -a
    gcloud components install docker-credential-gcr && \
    docker-credential-gcloud configure-docker && \
    docker build -t ${var.image} . && \
    docker push ${var.image}
   EOT
  }
  depends_on = [google_project_service.containerregistry]

  // uncomment the following trigger to deploy image
  //triggers = {
  //  always_run = timestamp()
  //}
}

/* Assign access to default service to bucket created for Container registry  */
resource "google_project_service" "containerregistry" {
  service          = "containerregistry.googleapis.com"
  disable_on_destroy = false
}