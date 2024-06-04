package main

import (
	"GRPC_example/internal/config"
	"GRPC_example/internal/repository/mongo"
	"GRPC_example/internal/service"
	"GRPC_example/proto"
	"context"
	"fmt"
	"github.com/spf13/viper"
	"google.golang.org/grpc"
	"log"
	"net"
)

var (
	host = "localhost"
	port = "5000"
)

func main() {
	ctx := context.Background()

	err := SetUpViper()

	if err != nil {
		log.Fatalf("error reading uml file: %v", err)
	}

	addr := fmt.Sprintf("%s:%s", host, port)

	lis, err := net.Listen("tcp", addr)

	if err != nil {
		log.Fatalf("error starting tcp listener: %v", err)
	}

	mongoDatabase, err := config.SetupMongoDataBase(ctx)

	if err != nil {
		log.Fatalf("error starting mongo: %v", err)
	}

	userRepository := mongo.NewUserRepository(mongoDatabase.Collection("users"))
	userService := service.NewUserService(userRepository)

	grpcServer := grpc.NewServer()

	proto.RegisterUserServiceServer(grpcServer, userService)

	log.Printf("gRPC started at %v\n", port)

	if err := grpcServer.Serve(lis); err != nil {
		log.Fatalf("error starting gRPC: %v", err)
	}
}

func SetUpViper() error {
	viper.AddConfigPath("configs")
	viper.SetConfigName("config")

	if err := viper.ReadInConfig(); err != nil {
		return err
	}

	return nil
}
