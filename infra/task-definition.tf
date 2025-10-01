resource "aws_ecs_task_definition" "ecs_task_definition" {
  family = "app-task-definition"
  network_mode = "awsvpc"
  cpu = 512
  memory = 1024
  requires_compatibilities = ["FARGATE"]
  execution_role_arn = aws_iam_role.ecs_task_execution_role.arn

  container_definitions = jsonencode([
    {
      name = "app-container"
#       image = "roginhooo/jwt-validator:latest"
      image = "191384364473.dkr.ecr.us-east-1.amazonaws.com/application-repository:latest"
#       image = aws_ecr_repository.application_repository.repository_url
      cpu = 256
      memory = 512
      essential = true
      portMappings = [{
        containerPort = 80
        hostPort = 80
        protocol = "tcp"
      }]
      logConfiguration = {
        logDriver = "awslogs"
        options = {
          "awslogs-group" = aws_cloudwatch_log_group.cloudwatch_log.name
          "awslogs-region" = "us-east-1"
          "awslogs-stream-prefix" = "ecs"
        }
      }
    }
  ])
}