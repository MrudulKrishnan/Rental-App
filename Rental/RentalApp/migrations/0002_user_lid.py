# Generated by Django 4.2 on 2023-08-16 05:53

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('RentalApp', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='user',
            name='LID',
            field=models.ForeignKey(default=1, on_delete=django.db.models.deletion.CASCADE, to='RentalApp.login'),
            preserve_default=False,
        ),
    ]
