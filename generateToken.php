<?php
function generateAccessToken() {
    if (function_exists('mcrypt_create_iv')) {
        $randomData = mcrypt_create_iv(20, MCRYPT_DEV_URANDOM);
        if ($randomData !== false && strlen($randomData) === 20) {
            return bin2hex($randomData);
        }
    }
    if (function_exists('openssl_random_pseudo_bytes')) {
        $randomData = openssl_random_pseudo_bytes(20);
        if ($randomData !== false && strlen($randomData) === 20) {
            return bin2hex($randomData);
        }
    }
    if (@file_exists('/dev/urandom')) { // Get 100 bytes of random data
        $randomData = file_get_contents('/dev/urandom', false, null, 0, 20);
        if ($randomData !== false && strlen($randomData) === 20) {
            return bin2hex($randomData);
        }
    }
    // Last resort which you probably should just get rid of:
    $randomData = mt_rand() . mt_rand() . mt_rand() . mt_rand() . microtime(true) . uniqid(mt_rand(), true);
    return substr(hash('sha512', $randomData), 0, 40);
}

function genToken($len = 32, $md5 = true) {
    # Seed random number generator
    # Only needed for PHP versions prior to 4.2
    mt_srand((double)microtime() * 1000000);
    # Array of characters, adjust as desired
    $chars = array(
        'Q',
        '@',
        '8',
        'y',
        '%',
        '^',
        '5',
        'Z',
        '(',
        'G',
        '_',
        'O',
        '`',
        'S',
        '-',
        'N',
        '<',
        'D',
        '{',
        '}',
        '[',
        ']',
        'h',
        ';',
        'W',
        '.',
        '/',
        '|',
        ':',
        '1',
        'E',
        'L',
        '4',
        '&',
        '6',
        '7',
        '#',
        '9',
        'a',
        'A',
        'b',
        'B',
        '~',
        'C',
        'd',
        '>',
        'e',
        '2',
        'f',
        'P',
        'g',
        ')',
        '?',
        'H',
        'i',
        'X',
        'U',
        'J',
        'k',
        'r',
        'l',
        '3',
        't',
        'M',
        'n',
        '=',
        'o',
        '+',
        'p',
        'F',
        'q',
        '!',
        'K',
        'R',
        's',
        'c',
        'm',
        'T',
        'v',
        'j',
        'u',
        'V',
        'w',
        ',',
        'x',
        'I',
        '$',
        'Y',
        'z',
        '*'
    );
    # Array indice friendly number of chars;
    $numChars = count($chars) - 1;
    $token = '';
    # Create random token at the specified length
    for ($i = 0; $i < $len; $i++) $token .= $chars[mt_rand(0, $numChars)];
    # Should token be run through md5?
    if ($md5) {
        # Number of 32 char chunks
        $chunks = ceil(strlen($token) / 32);
        $md5token = '';
        # Run each chunk through md5
        for ($i = 1; $i <= $chunks; $i++) $md5token .= md5(substr($token, $i * 32 - 32, 32));
        # Trim the token
        $token = substr($md5token, 0, $len);
    }
    return $token;
}
