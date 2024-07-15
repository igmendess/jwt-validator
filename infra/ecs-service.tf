resource "aws_ecs_service" "ecs_service" {
    name = "app-service"
    cluster = aws_ecs_cluster.ecs_cluster.id
    desired_count = 1
    task_definition = aws_ecs_task_definition.ecs_task_definition.arn
    launch_type = "FARGATE"

    network_configuration {
        security_groups = [aws_security_group.security_group.id]
        subnets = [aws_subnet.public_subnet1.id, aws_subnet.public_subnet2.id]
        assign_public_ip = true
    }

    load_balancer {
        target_group_arn = aws_lb_target_group.ecs_target_group.arn
        container_name = "app-container"
        container_port = 80
    }

    depends_on = [
        aws_ecs_cluster.ecs_cluster,
        aws_lb.ecs_load_balancer
    ]

    tags = {
        Name = "ecs-service"
    }
}