terraform {
  required_providers {
    aws = {
      source = "hashicorp/aws"
      version = "5.57.0"
    }
  }
}

provider "aws" {
  region="us-east-1"
}

resource "aws_s3_bucket" "igmendess-bucket-terraform-statefile-validator-api" {
  bucket = "bucket-terraform-statefile-validator-api"
  force_destroy = true  # opcional: permite destruir o bucket mesmo com objetos dentro

  tags = {
    Name        = validator-api
  }
}