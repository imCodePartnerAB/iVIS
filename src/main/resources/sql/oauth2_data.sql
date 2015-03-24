# USE db_ivis;
DELETE FROM oauth_client_details;
INSERT INTO oauth_client_details
  (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES
  ('ivis_info', 'ivis', 'secret', 'read,write', 'authorization_code,implicit,refresh-token,client-credentials,password', NULL, 'ROLE_CLIENT', 120, 300, NULL, NULL),
  ('ivis_info_redirect', 'ivis', 'secret', 'read,write', 'authorization_code, implicit,refresh-token, client-credentials,password', 'http://localhost:8083/web2/ivis/redirect', 'ROLE_CLIENT', 120, 300, NULL, NULL),
  ('ivis', 'ivis', 'secret', 'read,write', 'authorization_code,implicit', NULL, 'ROLE_CLIENT', 120, 300, NULL, '1');