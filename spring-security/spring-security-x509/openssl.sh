#!/bin/sh

set -e

rm -rfv .openssl *.csr *.key *.jks *.p12 *.pem

openssl req -new -x509 -days 365 -extensions v3_ca -subj "/CN=root CA/OU=spring-security-x509/O=Spring Samples by unmacaque/ST=Baden-Wuerttemberg/C=DE/" -keyout rootca.key -out rootca.pem -nodes
keytool -import -noprompt -file rootca.pem -storepass trustpass -alias "root CA" -keystore trust.jks

mkdir -p .openssl/newcerts
touch .openssl/index.txt
echo 1000 > .openssl/serial

for identity in client server; do
  openssl req -new -config openssl.cnf -subj "/CN=$identity/OU=spring-security-x509/O=Spring Samples by unmacaque/ST=Baden-Wuerttemberg/C=DE/" -keyout $identity.key -out $identity.csr -nodes
  openssl ca -batch -days 365 -config openssl.cnf -keyfile rootca.key -cert rootca.pem -in $identity.csr -out $identity.pem
  openssl pkcs12 -export -passout pass:storepass -inkey $identity.key -in $identity.pem -certfile rootca.pem -name $identity -out $identity.p12

  cp -fv $identity.p12 trust.jks spring-security-x509-$identity/src/main/resources
done
