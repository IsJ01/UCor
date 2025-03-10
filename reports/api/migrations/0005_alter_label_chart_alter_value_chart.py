# Generated by Django 5.1 on 2025-03-04 16:19

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0004_report_file'),
    ]

    operations = [
        migrations.AlterField(
            model_name='label',
            name='chart',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='labels', to='api.chart'),
        ),
        migrations.AlterField(
            model_name='value',
            name='chart',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='values', to='api.chart'),
        ),
    ]
