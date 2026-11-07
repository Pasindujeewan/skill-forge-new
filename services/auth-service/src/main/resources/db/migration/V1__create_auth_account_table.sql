
CREATE TABLE auth_accounts (
                               id UUID PRIMARY KEY,

                               email VARCHAR(255) NOT NULL UNIQUE,

                               password_hash VARCHAR(255) NOT NULL,

                               account_status VARCHAR(30) NOT NULL
                                   DEFAULT 'PENDING_VERIFICATION',

                               email_verified BOOLEAN NOT NULL
                                   DEFAULT FALSE,

                               created_at TIMESTAMP WITH TIME ZONE NOT NULL,

                               updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);
