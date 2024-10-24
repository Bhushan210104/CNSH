import hmac
import hashlib

def generate_hmac(message, key):

    hmac_obj = hmac.new(key.encode(), message.encode(), hashlib.sha256)

    return hmac_obj.hexdigest()

if __name__ == "__main__":
    
    message = "The quick brown fox jumps over the lazy dog"
    key = "secret_key"

    hmac_value = generate_hmac(message, key)
    print("Message:", message)
    print("Key:", key)
    print("HMAC:", hmac_value)
