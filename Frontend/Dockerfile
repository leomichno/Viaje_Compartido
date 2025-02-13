# Etapa de construcción
FROM node:18 AS build

# Instalar Bun (asegúrate de que se instala correctamente)
RUN curl -fsSL https://bun.sh/install | bash
# Agregar Bun al PATH
ENV PATH="/root/.bun/bin:$PATH"

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos de dependencias
COPY package.json bun.lockb ./

# Instalar dependencias con Bun
RUN bun install

# Copiar el resto de la aplicación
COPY . .

# Construir la aplicación
RUN bun run build

# Etapa de producción
FROM nginx:alpine

# Copiar los archivos construidos
COPY --from=build /app/dist /usr/share/nginx/html

# Exponer el puerto 80
EXPOSE 80

# Ejecutar nginx
CMD ["nginx", "-g", "daemon off;"]