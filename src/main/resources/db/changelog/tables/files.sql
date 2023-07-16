create table files(
    id BIGSERIAL PRIMARY KEY,
    filename varchar (256) unique not null,
    editedat timestamp,
    size bigint not null,
    filecontent bytea not null,
    user_id bigserial references users(id) on delete set null
);
