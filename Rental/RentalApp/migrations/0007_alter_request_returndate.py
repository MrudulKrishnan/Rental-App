# Generated by Django 3.2.20 on 2023-10-25 08:36

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('RentalApp', '0006_alter_request_returndate'),
    ]

    operations = [
        migrations.AlterField(
            model_name='request',
            name='ReturnDate',
            field=models.DateField(),
        ),
    ]