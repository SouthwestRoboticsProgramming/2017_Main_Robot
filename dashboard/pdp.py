import pygame, json, time
from networktables import NetworkTables

time.sleep(0.1)
NetworkTables.initialize("172.22.11.2")
time.sleep(0.1)
sd=NetworkTables.getTable("SmartDashboard")
time.sleep(0.1)
# assert NetworkTables.isConnected(), "Not connected"
def r():
	SCREEN_X=950
	SCREEN_Y=650
	screen=pygame.display.set_mode((SCREEN_X,SCREEN_Y))

	image=pygame.image.load("pdp_scaled.png").convert()
	image.set_colorkey(image.get_at((0,0)))

	BOARD_X=BOARD_Y=100

	pos_data=[[num, x+BOARD_X,y+BOARD_Y, dist] for num,x,y,dist in json.load(open("positions.json", 'r'))["positions"]]

	class Position:
		def __init__(self, num, pos, down, neg_dist, width):
			self.num=num
			self.pos=pos
			self.down=down
			self.width=width
			self.neg_dist=neg_dist
			self.speed=0
			self.state=0

		def draw(self, screen):
			DISTANCE=5
			HWIDTH=self.width/2
			green=(0,225,0) if abs(self.speed)>0 else (0,120,0)
			red=(225,0,0) if abs(self.speed)>0 else (120,0,0)
			self.state+=self.speed
			if abs(self.state)>DISTANCE:
				self.state=0
			xpos=self.pos[0]
			if self.down:
				ypos=self.pos[1]+self.state
				while ypos<SCREEN_Y:
					pygame.draw.line(screen, green if self.speed>0 else red, (xpos-HWIDTH, ypos), (xpos+HWIDTH, ypos), 2)
					ypos+=DISTANCE
				xpos+=self.neg_dist
				ypos=SCREEN_Y-self.state
				while ypos>self.pos[1]+self.state:
					pygame.draw.line(screen, red if self.speed>0 else green, (xpos-HWIDTH, ypos), (xpos+HWIDTH, ypos), 2)
					ypos-=DISTANCE
			else:
				ypos=self.pos[1]+self.state
				while ypos>0:
					pygame.draw.line(screen, red if self.speed>0 else green, (xpos-HWIDTH, ypos), (xpos+HWIDTH, ypos), 2)
					ypos-=DISTANCE
				xpos+=self.neg_dist
				ypos=0-self.state
				while ypos<self.pos[1]+self.state:
					pygame.draw.line(screen, green if self.speed>0 else red, (xpos-HWIDTH, ypos), (xpos+HWIDTH, ypos), 2)
					ypos+=DISTANCE

	positions={num:Position(num, (x,y), num<8, d, 15 if num in [0,1,2,3,12,13,14,15] else 8) for num, x, y, d in pos_data}

	# for pos in set(positions.keys()):
	# 	if pos not in [4,11,1,2,3,12,13,14,15]:
	# 		del positions[pos]

	positions[4].speed=0.1
	positions[11].speed=-0.3
	positions[12].speed=-0.7
	positions[13].speed=0.1

	clock=pygame.time.Clock()

	run=1
	while run:
		clock.tick(60)
		screen.fill((0,0,0))
		screen.blit(image, (BOARD_X,BOARD_Y))
		for num, v in enumerate(sd.getString("pdp_status").split(";")[1:]):
			if num in positions:
				positions[num].speed=float(v)*(1/12)
		[p.draw(screen) for p in positions.values()]
		pygame.display.flip()

		for e in pygame.event.get():
			if e.type==pygame.QUIT:
				run=0

r()