import pygame
from .text import render_text
from .core import translate_event

class Percent:
	def __init__(self, num):
		self.pct=num

	def resolve(self, total):
		return (self.pct/100)*total

	def __rmod__(self, num):
		return Percent(num)

def resolve_size(size, total): return size.resolve(total) if isinstance(size, Percent) else size

percent=Percent(None)

class Widget:
	target_fps=60

	def _init_widget(self):
		self.size=self.img=None
		# self.img=None
		# self.draw()
		# if self.img is None:
		# 	self.size=(0,0)
		# else:
		# 	self.size=self.img.get_size()

	def set_parent(self, wid):
		self.parent=wid

	def draw_to_surface(self, surf, position):
		surf.blit(self.img, position)

	def update(self):
		"""Will be targeted to be called target_fps times a second. Should
		update internal state so that draw_to_surface will contain the
		latest state. Actual delta time should be got via maui.ctx.dt"""
		self.draw()

	def draw(self):
		pass

	def get_layout_request(self):
		self.update()
		return self.size

	def set_layout(self, xsz, ysz):
		if self.size is None or self.size[0]!=xsz or self.size[1]!=ysz:
			self.size=(xsz, ysz)
			self.update()
		else:
			self.size=(xsz, ysz)

	def handle_event(self, event):
		pass

class ContainerWidget(Widget):
	def _init_container(self):
		self.children=[]
		self.child_positions=None

	def add_child(self, wid):
		self.children.append(wid)
		self.child_positions=None
		wid.set_parent(self)

	def recalculate_child_positions(self):
		pass

	def update(self):
		if self.child_positions is None: self.recalculate_child_positions()
		[child.update() for child in self.children]

	def draw_to_surface(self, surf, pos):
		for wid in self.children:
			wid.draw_to_surface(surf, (pos[0]+self.child_positions[wid][0], pos[1]+self.child_positions[wid][1]))

	def get_layout_request(self):
		return [100%percent, 100%percent]

	def handle_event(self, event):
		for wid in self.children:
			if 'pos' in dir(event):
				if wid.img.get_rect().move(*self.child_positions[wid]).collidepoint(event.pos):
					wid.handle_event(translate_event(event, (-self.child_positions[wid][0], -self.child_positions[wid][1])))
			else:
				wid.handle_event(event)

class VerticalListWidget(ContainerWidget):
	def __init__(self, children):
		self._init_widget()
		self._init_container()
		[self.add_child(c) for c in children]

	def recalculate_child_positions(self):
		self.child_positions={}
		total_vertical_room=self.size[1]
		current_position=0
		for child in self.children:
			self.child_positions[child]=(0, current_position)
			child_height=resolve_size(child.get_layout_request()[1], total_vertical_room)
			child.set_layout(resolve_size(child.get_layout_request()[0], self.size[0]), child_height)
			current_position+=child_height
			if current_position>total_vertical_room:
				print("WARNING: MAUI.widget.VerticalListWidget: current_position>total_vertical_room when recalculating children")
				print("\t\t... %i>%i (d=%i)"%(current_position, total_vertical_room, current_position-total_vertical_room))

pygame.font.init()
class TextWidget(Widget):
	def __init__(self, text, font=pygame.font.SysFont("monospace", 16), color=(255,255,255), bgcolor=None, boxcolor=None, aa=0):
		self.text=text
		self.font=font
		self.color=color
		self.bgcolor=bgcolor
		self.boxcolor=boxcolor
		self.aa=aa
		self._init_widget()

	def draw(self):
		text=render_text(self.font, self.text, self.color, self.aa, self.bgcolor).convert_alpha()
		if self.boxcolor is None:
			self.img=text
		else:
			self.img=pygame.Surface(text.get_size()).convert_alpha()
			self.img.fill(self.boxcolor)
			self.img.blit(text, (0,0))
		self.size=text.get_size()

	def handle_event(s, e):
		if e.type==pygame.MOUSEBUTTONDOWN:
			s.boxcolor=(100,0,0) if s.boxcolor==None else None