from django.db import models

# Create your models here.


class Login(models.Model):
    objects = None
    Username = models.CharField(max_length=50)
    Password = models.CharField(max_length=50)
    Type = models.CharField(max_length=20)


class User(models.Model):
    objects = None
    Firstname = models.CharField(max_length=50)
    Lastname = models.CharField(max_length=50)
    Place = models.CharField(max_length=50)
    Post = models.CharField(max_length=50)
    Pin = models.IntegerField()
    Phone = models.BigIntegerField()
    Email = models.CharField(max_length=50)
    Proof = models.ImageField()
    LID = models.ForeignKey(Login, on_delete=models.CASCADE)


class Product(models.Model):
    objects = None
    Product_name = models.CharField(max_length=100)
    Product_type = models.CharField(max_length=100)
    Details = models.CharField(max_length=100)
    Price_per_day = models.CharField(max_length=50)
    image = models.ImageField()
    UID = models.ForeignKey(User, on_delete=models.CASCADE)


class Chat(models.Model):
    Chat1 = models.CharField(max_length=300)
    Date = models.CharField(max_length=20)
    Time = models.CharField(max_length=20)
    From_id = models.ForeignKey(User, on_delete=models.CASCADE, related_name="from_id")
    To_id = models.ForeignKey(User, on_delete=models.CASCADE, related_name="to_id")


class Complaint(models.Model):
    Complaint1 = models.CharField(max_length=300)
    Date = models.CharField(max_length=20)
    Reply = models.CharField(max_length=300)
    UID = models.ForeignKey(User, on_delete=models.CASCADE)


class Request(models.Model):
    objects = None
    Date = models.CharField(max_length=20)
    Status = models.CharField(max_length=100)
    ReturnDate = models.CharField(max_length=200)
    UID = models.ForeignKey(User, on_delete=models.CASCADE)
    PID = models.ForeignKey(Product, on_delete=models.CASCADE)

