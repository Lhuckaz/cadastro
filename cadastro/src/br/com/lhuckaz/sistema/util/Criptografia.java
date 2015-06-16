package br.com.lhuckaz.sistema.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Criptografia {

	/** Obtém o "hash" de uma senha para um dado "salt". */
	public String getHash(String senha, String salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		/*
		 * "MD2", "MD5", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512",
		 * "MD4", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512",
		 * "RIPEMD128", "RIPEMD160", "RIPEMD256", "RIPEMD320", "Tiger",
		 * "DHA256", e "FORK256".
		 */
		String algoritmo = "SHA-1";
		MessageDigest condensador = MessageDigest.getInstance(algoritmo);

		condensador.reset();
		condensador.update(this.base64ToByte(salt));

		byte[] hash = condensador.digest(senha.getBytes("UTF-8"));

		for (int i = 0; i < 1000; i++) {
			condensador.reset();
			hash = condensador.digest(hash);
		}

		return this.byteToBase64(hash);
	}

	/** Gera um "salt" aleatório de 64 bit. */
	public String getSaltAleatorio() throws NoSuchAlgorithmException {
		byte[] salt = new byte[8];
		String algoritmo = "SHA1PRNG";
		SecureRandom random = SecureRandom.getInstance(algoritmo);
		random.nextBytes(salt);
		return this.byteToBase64(salt);
	}

	/** @see Decoder#decode(byte[]) */
	private byte[] base64ToByte(String base64) {
		return Base64.getDecoder().decode(base64);
	}

	/** @see Encoder#encodeToString(byte[]) */
	private String byteToBase64(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

}