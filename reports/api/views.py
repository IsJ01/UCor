from rest_framework import generics

from .serializers import ReportSerializer
from .models import Report


class ReportListView(generics.ListCreateAPIView):
    serializer_class = ReportSerializer
    queryset = Report.objects.all()


class ReportDetailView(generics.RetrieveUpdateDestroyAPIView):
    serializer_class = ReportSerializer
    queryset = Report.objects.all()
