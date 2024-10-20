from rest_framework import generics

from .serializers import OrganizationPropertySerializer
from .models import OrganizationProperty


class OrganizationDataListView(generics.ListCreateAPIView):
    serializer_class = OrganizationPropertySerializer
    queryset = OrganizationProperty.objects.all()


class OrganizationDataDetailView(generics.RetrieveUpdateDestroyAPIView):
    serializer_class = OrganizationPropertySerializer
    queryset = OrganizationProperty.objects.all()