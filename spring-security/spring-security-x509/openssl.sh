#!/bin/sh

set -e

rm -rfv .tmp-ssl
mkdir -v .tmp-ssl
cd .tmp-ssl

openssl req -new -x509 -days 3650 -extensions v3_ca -subj "/C=DE/ST=Baden-Wuerttemberg/O=Spring Samples by unmacaque/OU=spring-security-x509/CN=root CA/" -keyout rootca.key -out rootca.crt -nodes
keytool -import -noprompt -file rootca.crt -storepass trustpass -alias "root CA" -keystore trust.jks

for identity in client server; do
  openssl req -new -subj "/C=DE/ST=Baden-Wuerttemberg/O=Spring Samples by unmacaque/OU=spring-security-x509/CN=localhost/" -keyout ${identity}.key -out ${identity}.csr -nodes
  openssl x509 -req -days 3650 -CAkey rootca.key -CA rootca.crt -CAcreateserial -in ${identity}.csr -out ${identity}.crt
  openssl pkcs12 -export -passout pass:storepass -inkey ${identity}.key -in ${identity}.crt -name ${identity} -out ${identity}.p12

  cp -fv ${identity}.p12 trust.jks ../src/main/resources
done
