# Generated by Django 3.2.20 on 2023-10-25 08:35

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('RentalApp', '0005_rename_complaint_complaint_complaint1'),
    ]

    operations = [
        migrations.AlterField(
            model_name='request',
            name='ReturnDate',
            field=models.CharField(max_length=100),
        ),
    ]
