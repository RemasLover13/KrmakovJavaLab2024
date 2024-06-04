package core

import (
	"go.mongodb.org/mongo-driver/bson/primitive"
)

type User struct {
	ID        primitive.ObjectID `bson:"_id,omitempty"`
	FirstName string             `bson:"firstname,omitempty"`
	LastName  string             `bson:"lastname,omitempty"`
}
