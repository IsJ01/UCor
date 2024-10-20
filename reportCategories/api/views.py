from rest_framework import generics

from .serializers import ReportCategorySerializer
from .models import ReportCategory


class ReportCategoryListView(generics.ListCreateAPIView):
    serializer_class = ReportCategorySerializer
    queryset = ReportCategory.objects.all()


class ReportCategoryDetailView(generics.RetrieveUpdateDestroyAPIView):
    serializer_class = ReportCategorySerializer
    queryset = ReportCategory.objects.all()