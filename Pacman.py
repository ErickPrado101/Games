import pygame
import random

# Definir as cores
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)
BLUE = (0, 0, 255)
YELLOW = (255, 255, 0)
GREEN = (0, 255, 0)
RED = (255, 0, 0)

# Definir a largura e a altura da tela
SCREEN_WIDTH = 800
SCREEN_HEIGHT = 600

# Classe do jogador
class Player(pygame.sprite.Sprite):
    def __init__(self):
        super().__init__()
        self.image = pygame.Surface([15, 15])
        self.image.fill(YELLOW)
        self.rect = self.image.get_rect()
        self.rect.x = SCREEN_WIDTH // 2
        self.rect.y = SCREEN_HEIGHT // 2
        self.speed_x = 0
        self.speed_y = 0

    def update(self):
        self.rect.x += self.speed_x
        self.rect.y += self.speed_y
        if self.rect.left < 0:
            self.rect.left = 0
        elif self.rect.right > SCREEN_WIDTH:
            self.rect.right = SCREEN_WIDTH
        elif self.rect.top < 0:
            self.rect.top = 0
        elif self.rect.bottom > SCREEN_HEIGHT:
            self.rect.bottom = SCREEN_HEIGHT

# Classe dos fantasmas
class Ghost(pygame.sprite.Sprite):
    def __init__(self, color):
        super().__init__()
        self.image = pygame.Surface([20, 20])
        self.image.fill(color)
        self.rect = self.image.get_rect()
        self.rect.x = random.randrange(SCREEN_WIDTH - self.rect.width)
        self.rect.y = random.randrange(SCREEN_HEIGHT - self.rect.height)
        self.speed_x = random.randrange(-3, 4)
        self.speed_y = random.randrange(-3, 4)

    def update(self):
        self.rect.x += self.speed_x
        self.rect.y += self.speed_y
        if self.rect.left < 0 or self.rect.right > SCREEN_WIDTH:
            self.speed_x *= -1
        elif self.rect.top < 0 or self.rect.bottom > SCREEN_HEIGHT:
            self.speed_y *= -1

# Inicializar o Pygame
pygame.init()

# Definir a fonte
font = pygame.font.SysFont("calibri", 30)

# Definir a tela
screen = pygame.display.set_mode([SCREEN_WIDTH, SCREEN_HEIGHT])

# Definir o título do jogo
pygame.display.set_caption("Pacman Game")

# Criar os grupos de sprites
all_sprites = pygame.sprite.Group()
ghost_sprites = pygame.sprite.Group()

# Criar o jogador
player = Player()
all_sprites.add(player)

# Criar os fantasmas
for i in range(4):
    ghost = Ghost(RED)
    ghost_sprites.add(ghost)
    all_sprites.add(ghost)

# Definir o clock
clock = pygame.time.Clock()

# Definir a pontuação
score = 0

# Loop principal do jogo
running = True
while running:

    # Processar os eventos
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_LEFT:
                player.speed_x = -3
            elif event.key == pygame.K_RIGHT:
                player.speed_x = 3
            elif event.key == pygame.K_UP: