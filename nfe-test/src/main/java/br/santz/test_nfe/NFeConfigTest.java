package br.santz.test_nfe;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import java.security.cert.CertificateException;

import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.nfe.NFeConfig;

//Exemplo de configuracao para acesso aos serviços da Sefaz.
public class NFeConfigTest extends NFeConfig {

 private KeyStore keyStoreCertificado = null;
 private KeyStore keyStoreCadeia = null;

 @Override
 public DFUnidadeFederativa getCUF() {
     return DFUnidadeFederativa.ES;
 }

 @Override
 public String getCertificadoSenha() {
     return "test123";
 }

 @Override
 public String getCadeiaCertificadosSenha() {
     return "test123";
 }

 @Override
 public KeyStore getCertificadoKeyStore() throws KeyStoreException {
     if (this.keyStoreCertificado == null) {
         this.keyStoreCertificado = KeyStore.getInstance("PKCS12");
         try (InputStream certificadoStream = new FileInputStream("/tmp/certificado.pfx")) {
             this.keyStoreCertificado.load(certificadoStream, this.getCertificadoSenha().toCharArray());
         } catch (CertificateException | NoSuchAlgorithmException | IOException e) {
             this.keyStoreCadeia = null;
             throw new KeyStoreException("Nao foi possibel montar o KeyStore com a cadeia de certificados", e);
         }
     }
    
     return this.keyStoreCertificado;
 }

 @Override
 public KeyStore getCadeiaCertificadosKeyStore() throws KeyStoreException {
     if (this.keyStoreCadeia == null) {
         this.keyStoreCadeia = KeyStore.getInstance("JKS");
         try (InputStream cadeia = new FileInputStream("/tmp/cadeia.jks")) {
             this.keyStoreCadeia.load(cadeia, this.getCadeiaCertificadosSenha().toCharArray());
         } catch (CertificateException | NoSuchAlgorithmException | IOException e) {
             this.keyStoreCadeia = null;
             throw new KeyStoreException("Nao foi possibel montar o KeyStore com o certificado", e);
         }
     }
     return this.keyStoreCadeia;
 }
}