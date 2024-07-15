resource "aws_lb_target_group" "ecs_target_group" {
    name = "app-target-group"
    port = 80
    protocol = "HTTP"
    target_type = "ip"
    vpc_id = aws_vpc.vpc.id

    health_check {
      path = "/"
      protocol = "HTTP"
      interval = 30
      timeout = 10
      healthy_threshold = 3
      unhealthy_threshold = 3
    }

    tags = {Name = "Target group"}
}

resource "aws_lb" "ecs_load_balancer" {
  name = "ecs-load-balancer"
  internal = false
  load_balancer_type = "application"
  security_groups = [aws_security_group.security_group.id]
  subnets = [
    aws_subnet.public_subnet1.id,
    aws_subnet.public_subnet2.id
  ]

  tags = {
    Name = "Load Balancer"
  }
}

resource "aws_lb_listener" "ecs_alb_listener" {
    load_balancer_arn = aws_lb.ecs_load_balancer.arn
    port = 80
    protocol = "HTTP"

    default_action {
      type = "forward"
      target_group_arn = aws_lb_target_group.ecs_target_group.arn
    }
}