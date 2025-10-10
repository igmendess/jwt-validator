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

resource "aws_s3_bucket" "bucket" {
  bucket = "igmendess-bucket-terraform-statefile-validator-api"
}