import kotlin.random.Random

fun main() {
    val inputMessage = getInput("Введите исходное сообщение:")
    val key = getInput("Введите ключ:")

    val encryptedMessage = encryptVigenere(inputMessage, key)
    val vigenereTable = generateVigenereTable()

    println("Исходное сообщение: $inputMessage")
    println("Ключ: $key")
    println("Зашифрованное сообщение: $encryptedMessage")
    println("Шифровальная таблица:")
    printVigenereTable(vigenereTable)
}

fun getInput(prompt: String): String {
    println(prompt)
    return readLine() ?: ""
}

fun encryptVigenere(message: String, key: String): String {
    val alphabet = ('А'..'Я').toList()
    val encryptedChars = mutableListOf<Char>()
    val keyChars = key.toUpperCase().toCharArray()
    var keyIndex = 0

    for (char in message) {
        val charIndex = alphabet.indexOf(char.toUpperCase())
        if (charIndex != -1) {
            val shift = alphabet.indexOf(keyChars[keyIndex % keyChars.size])
            val encryptedCharIndex = (charIndex + shift) % alphabet.size
            encryptedChars.add(alphabet[encryptedCharIndex])
            keyIndex++
        } else {
            encryptedChars.add(char)
        }
    }

    return encryptedChars.joinToString("")
}

fun generateVigenereTable(): List<List<Char>> {
    val alphabet = ('А'..'Я').toList()
    val randomShifts = List(33) { Random.nextInt(33) }

    return List(33) { i ->
        val shift = randomShifts[i]
        alphabet.subList(shift, alphabet.size) + alphabet.subList(0, shift)
    }
}

fun printVigenereTable(table: List<List<Char>>) {
    table.forEach { row -> println(row.joinToString(" ")) }
}