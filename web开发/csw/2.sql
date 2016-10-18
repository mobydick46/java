insert into mysql.user(host, user, password, select_priv, insert_priv, update_priv) 
    VALUES ('localhost', 'csw', PASSWORD('csw'), 'Y', 'Y', 'Y');  

grant all privileges on csw.* to csw@localhost identified by 'csw';  


