#!/bin/sh

# Function to check if a process is running
check_process() {
    if [ -f "$1" ]; then
        pid=$(cat "$1")
        if [ -n "$pid" ] && kill -0 "$pid" 2>/dev/null; then
            return 0
        fi
    fi
    return 1
}

# Function to start nginx
start_nginx() {
    echo "Starting Nginx..."
    nginx
    if [ $? -eq 0 ]; then
        echo "Nginx started successfully"
    else
        echo "Failed to start Nginx"
        exit 1
    fi
}

# Function to start Spring Boot application
start_spring_boot() {
    echo "Starting Spring Boot application..."
    java -jar /app/application.jar &
    SPRING_PID=$!
    echo $SPRING_PID > /var/run/spring-boot.pid

    # Wait for Spring Boot to start
    echo "Waiting for Spring Boot to start..."
    sleep 10

    if kill -0 $SPRING_PID 2>/dev/null; then
        echo "Spring Boot application started successfully"
    else
        echo "Failed to start Spring Boot application"
        exit 1
    fi
}

# Function to gracefully shutdown services
shutdown() {
    echo "Shutting down services..."

    # Stop Spring Boot if running
    if [ -f /var/run/spring-boot.pid ]; then
        SPRING_PID=$(cat /var/run/spring-boot.pid)
        kill $SPRING_PID 2>/dev/null
        rm -f /var/run/spring-boot.pid
    fi

    # Stop Nginx
    nginx -s quit

    exit 0
}

# Set up signal trapping
trap shutdown SIGTERM SIGINT SIGQUIT

# Create necessary directories
mkdir -p /run/nginx
mkdir -p /var/log/nginx
mkdir -p /var/run

# Start services
start_nginx
start_spring_boot

# Keep the script running and watch for failures
while true; do
    # Check if Nginx is running
    if ! nginx -t >/dev/null 2>&1; then
        echo "Nginx failed, attempting to restart..."
        start_nginx
    fi

    # Check if Spring Boot is running
    if ! check_process "/var/run/spring-boot.pid"; then
        echo "Spring Boot application failed, attempting to restart..."
        start_spring_boot
    fi

    sleep 30
done