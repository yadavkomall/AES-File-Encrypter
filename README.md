# AES-File-Encrypter
Advance Encryption Algorithm (AES) is a block cipher.
The key size can be 128/192/256 bits.
Encrypts data in blocks of 128 bits each.
That means it takes 128 bits as input and outputs 128 bits of encrypted cipher text as output. AES relies on substitution-permutation network principle which means it is performed using a series of linked operations which involves replacing and shuffling of the input data.

AES considers each block as a 16 byte (4 byte x 4 byte = 128 ) grid in a column major arrangement.
[ b0 | b4 | b8 | b12 |
| b1 | b5 | b9 | b13 |
| b2 | b6 | b10| b14 |
| b3 | b7 | b11| b15 ]

Each round comprises of 4 steps :
SubBytes
ShiftRows
MixColumns
Add Round Key

The last round doesn’t have the MixColumns round.
The SubBytes does the substitution and ShiftRows and MixColumns performs the permutation in the algorithm.

SubBytes  :

This step implements the substitution.
In this step each byte is substituted by another byte. Its performed using a lookup table also called the S-box. This substitution is done in a way that a byte is never substituted by itself and also not substituted by another byte which is a compliment of the current byte. The result of this step is a 16 byte (4 x 4 ) matrix like before.
The next two steps implement the permutation.

ShiftRows :

This step is just as it sounds. Each row is shifted a particular number of times.
The first row is not shifted
The second row is shifted once to the left.
The third row is shifted twice to the left.
The fourth row is shifted thrice to the left.

MixColumns :

This step is basically a matrix multiplication. Each column is multiplied with a specific matrix and thus the position of each byte in the column is changed as a result.
This step is skipped in the last round.

Add Round Keys :

Now the resultant output of the previous stage is XOR-ed with the corresponding round key. Here, the 16 bytes is not considered as a grid but just as 128 bits of data.
After all these rounds 128 bits of encrypted data is given back as output. This process is repeated until all the data to be encrypted undergoes this process.

CODE EXPLAINATION -

1.File Selection:
The user selects a file to be encrypted using a JFileChooser dialog.
2.Reading the File:
The selected file is read into memory as a byte array. The contents of the file are stored in the data array.
3.Creating a Secret Key:
The encryption process requires a secret key. In this code, the key is provided as a byte[] array. The key should be a sequence of bytes with a specific length, depending on the AES key size (e.g., 16 bytes for AES-128, 24 bytes for AES-192, or 32 bytes for AES-256).
4.Initializing the Cipher:
The Cipher class is used to perform encryption. A Cipher object is created and initialized with the AES algorithm using the line Cipher cipher = Cipher.getInstance("AES");.
5.Creating a SecretKeySpec:
A SecretKeySpec object is created using the provided key byte array. The SecretKeySpec class is used to wrap the secret key as a key specification. It ensures that the key is in the correct format and can be used securely with the specified algorithm. The line SecretKeySpec secretKey = new SecretKeySpec(key, "AES"); accomplishes this.
6.Initializing the Cipher for Encryption:
The cipher.init(Cipher.ENCRYPT_MODE, secretKey); line initializes the Cipher object in encryption mode (Cipher.ENCRYPT_MODE) with the provided secret key.
7.Encryption:
The cipher.doFinal(data) method is called to encrypt the data array. The AES encryption process is performed on the entire data array, and the result is stored in the encryptedData array.
8.Writing the Encrypted Data Back to the File:
The encrypted data in the encryptedData array is written back to the same file using a FileOutputStream. This overwrites the original file with the encrypted data.
9.Displaying Encryption Completion:
A message dialog (JOptionPane.showMessageDialog) is shown to the user indicating that the encryption process is done.
During encryption, each block of plaintext is XORed with the previous ciphertext block (CBC mode) before encryption. The first block is XORed with an Initialization Vector (IV), which is provided implicitly by the cipher.init method. The IV ensures that even identical plaintext blocks result in different ciphertext blocks, providing an extra layer of security.
