Book Catalog Service

This is a simple book catalog service that allows you to add, edit, and delete books. It also allows you to search for books by title, author, or ISBN.

Work in progress, database is running as container postgres.

Dockerfile will be replaced by buildpack.

To generate container image:
    ./gradlew bootBuildImage --imageName=catalog-service:0.1
    