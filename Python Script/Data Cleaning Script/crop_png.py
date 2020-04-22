# Improting Image class from PIL module
from PIL import Image
import os.path

path = "E:/doc tesis/cod proyecto/Ouch/BD Imagenes and Videos/Test"
dirs = os.listdir(path)

# Function to recort multiple files
def main():
    i = 0
    for item in dirs:
        fullpath = os.path.join(path, item)  # corrected
        if os.path.isfile(fullpath):
            im = Image.open(fullpath)
            f, e = os.path.splitext(fullpath)
            imCrop = im.crop((0, 0, 870, 720))  # corrected
            imCrop.save(f + ".png", "PNG", quality=100)
            print(i)
            i += 1


# Calling main() function
if __name__ == '__main__':
    main()