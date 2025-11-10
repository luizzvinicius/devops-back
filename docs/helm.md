# Deployment Guide

## Steps
- Install the ingress controller
- Install the CloudNative Operator
- Install the Application Helm chart

## Ingress Controller Installation

[Nginx - controller](https://kubernetes.github.io/ingress-nginx/deploy/)

```bash
helm upgrade --install ingress-nginx ingress-nginx \
  --repo https://kubernetes.github.io/ingress-nginx \
  --namespace ingress-nginx --create-namespace
```

This command will install the controller in the `ingress-nginx` namespace, creating the namespace if it does not exist.

### Pre-flight Check

You should see several pods running in the `ingress-nginx` namespace:

```bash
kubectl get pods --namespace=ingress-nginx
```

## CloudNativePG Operator Installation

```bash
helm repo add cnpg https://cloudnative-pg.github.io/charts
helm upgrade --install cnpg \
  --namespace cnpg-system \
  --create-namespace \
  cnpg/cloudnative-pg
```

## Backend Helm Chart Installation

If you want to change the Helm release name, you must also update the service name in the database URL.

```bash
helm install devops-back ./helm 
```