# Generated by Django 3.2.20 on 2023-10-25 07:02

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('RentalApp', '0004_rename_chat_chat_chat1'),
    ]

    operations = [
        migrations.RenameField(
            model_name='complaint',
            old_name='Complaint',
            new_name='Complaint1',
        ),
    ]
