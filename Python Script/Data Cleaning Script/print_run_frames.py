import pyrealsense2 as rs
pipe = rs.pipeline()
profile = pipe.start()
try:
  #Imprime los datos de los 100 primeros frames que detecta la camara en vivo
  for i in range(0, 100):
    frames = pipe.wait_for_frames()
    for f in frames:
      print(f.profile)
finally:
    pipe.stop()