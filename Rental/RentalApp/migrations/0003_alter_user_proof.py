# Generated by Django 3.2.20 on 2023-09-11 04:02

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('RentalApp', '0002_user_lid'),
    ]

    operations = [
        migrations.AlterField(
            model_name='user',
            name='Proof',
            field=models.ImageField(upload_to=''),
        ),
    ]