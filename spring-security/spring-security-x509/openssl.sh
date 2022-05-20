#!/bin/sh

set -e

rm -rfv .tmp-ssl
mkdir -v .tmp-ssl
cd .tmp-ssl

openssl genrsa -out trust.key
openssl req -new -x509 -days 3650 -extensions v3_ca \
    -subj "/C=DE/ST=Baden-Wuerttemberg/O=Spring Samples by unmacaque/OU=spring-security-x509/CN=root CA/" \
    -key trust.key -out trust.pem -nodes
cp -fv trust.* ../src/main/resources

for identity in client server; do
    openssl genrsa -out ${identity}.key
    openssl req -new \
        -subj "/C=DE/ST=Baden-Wuerttemberg/O=Spring Samples by unmacaque/OU=spring-security-x509/CN=localhost/" \
        -key ${identity}.key -out ${identity}.csr -nodes
    openssl x509 -req -days 3650 -CAkey trust.key -CA trust.pem -CAcreateserial -in ${identity}.csr -out ${identity}.crt
    cat ${identity}.crt trust.pem > ${identity}.pem
    cp -fv ${identity}.pem ${identity}.key ../src/main/resources
done
