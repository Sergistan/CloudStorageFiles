create table files(
    id BIGSERIAL PRIMARY KEY,
    filename varchar (256) not null,
    editedAt timestamp,
    size bigint not null,
    fileContent bytea not null
);
