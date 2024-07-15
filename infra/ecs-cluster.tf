resource "aws_ecs_cluster" "ecs_cluster" {
  name = "ecs-cluster"
}

resource "aws_cloudwatch_log_group" "cloudwatch_log" {
  name = "/ecs/app-logs"
  retention_in_days = 7
}