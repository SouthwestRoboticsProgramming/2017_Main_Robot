import pygame, maui, maui.widget

pygame.font.init()

screen = pygame.display.set_mode((800, 600))
ui = maui.core.MAUI(screen, offset=(100,100))

ui.set_root(maui.widget.VerticalListWidget([
	maui.widget.TextWidget("Foo\nFoo 2\n\nFoo 3", boxcolor=(0,0,100)),
	maui.widget.TextWidget("Bar"),
	maui.widget.TextWidget("Baz"),
	maui.widget.TextWidget("Quux"),
	maui.widget.TextWidget("Box"),
	maui.widget.TextWidget("Bag")
]))

while 1:
	screen.fill((0,120,0))
	ui.update()
	ui.draw_to(screen, (100,100))
	pygame.display.flip()
	for e in pygame.event.get():
		ui.handle_event(e)
		if e.type==pygame.QUIT:
			1/0