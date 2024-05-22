#!/bin/bash

echo "Initializing localstack: " "${AWS_ENDPOINT}" " @ " "${AWS_DEFAULT_REGION}"

aws --endpoint-url="${AWS_ENDPOINT}" --region "${AWS_DEFAULT_REGION}" sns create-topic --name "modular-monolith-topic.fifo" --attributes FifoTopic=true
aws --endpoint-url="${AWS_ENDPOINT}" --region "${AWS_DEFAULT_REGION}" sqs create-queue --queue-name "modular-monolith-queue.fifo" --attributes FifoQueue=true
aws --endpoint-url="${AWS_ENDPOINT}" --region "${AWS_DEFAULT_REGION}" sqs create-queue --queue-name "integration-test-queue.fifo" --attributes FifoQueue=true
aws --endpoint-url="${AWS_ENDPOINT}" --region "${AWS_DEFAULT_REGION}" sns subscribe --topic-arn "arn:aws:sns:${AWS_DEFAULT_REGION}:000000000000:modular-monolith-topic.fifo" --protocol sqs --notification-endpoint "arn:aws:sqs:${AWS_DEFAULT_REGION}:000000000000:modular-monolith-queue.fifo"
aws --endpoint-url="${AWS_ENDPOINT}" --region "${AWS_DEFAULT_REGION}" sns subscribe --topic-arn "arn:aws:sns:${AWS_DEFAULT_REGION}:000000000000:modular-monolith-topic.fifo" --protocol sqs --notification-endpoint "arn:aws:sqs:${AWS_DEFAULT_REGION}:000000000000:integration-test-queue.fifo"

echo "Localstack initialization finished"
