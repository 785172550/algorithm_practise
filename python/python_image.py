from PIL import Image, ImageEnhance

#  ========== Example of using PIL for image processing
origin_image = Image.open("example.jpg")

# 通过调整亮度、对比度、饱和度提升图像质量。
# Enhance image quality by adjusting brightness, contrast, and color saturation.
bright = ImageEnhance.Brightness(origin_image).enhance(1.5)
contrast = ImageEnhance.Contrast(origin_image).enhance(1.5)
color = ImageEnhance.Color(origin_image).enhance(1.5)

from PIL import ImageFilter
# 利用高斯模糊和中值滤波去除噪声或还原图像清晰度。
# use Gaussian blur and median filter to remove noise or restore image clarity.
gaussian = origin_image.filter(ImageFilter.GaussianBlur(radius=5))
median = origin_image.filter(ImageFilter.MedianFilter(size=5))


# ========== example of reversing the image by PIL
reversed_img = origin_image.transpose(Image.FLIP_LEFT_RIGHT)  # Flip horizontally
reversed_img.save("reversed_example.jpg")  # Save the reversed image

reversed_img = origin_image.transpose(Image.FLIP_TOP_BOTTOM)  # Flip vertically
reversed_img = reversed_img.transpose(Image.ROTATE_90)  # Rotate 90 degrees clockwise
reversed_img.save("rotated_example.jpg")  # Save the rotated image


# ==== Example of using OpenCV for image processing
import cv2
import numpy as np

"""
img = Image.open('example.jpg')
img_array = np.array(img)
print(img_array.shape)  # (250, 400, 3) represent a 250x400 RGB image
"""

img = cv2.imread("example.jpg")
# 5x5 kernel for averaging filter
kernel = np.ones((5, 5), np.float32) / 25

# Apply the filter to the image, -1 means the output image will have the same depth as the input
filtered_img = cv2.filter2D(img, -1, kernel)


# ===== Example of using PyTorch for image processing
import torch
import torch.nn.functional as F
import numpy as np
import cv2  # 仅用于读图和存图

# 读图并转为张量(B, C, H, W)， (background, channel, height, width)
img = cv2.imread("image.jpg")
img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)  # OpenCV读入为BGR，常转换为RGB
img = img.transpose(2, 0, 1)  # (H,W,C)->(C,H,W)

# 1. 转换为PyTorch张量并归一化
img = img.astype(np.float32) / 255.0
# 增加一个batch维度
img_tensor = torch.tensor(img).unsqueeze(0)  # [1, 3, H, W]

# 2. 定义一个5x5的平均滤波器, shape为(3, 1, 5, 5)表示对RGB三个通道分别做卷积, (3 channels, 1 input channel, 5 height, 5 width)
kernel = torch.ones((3, 1, 5, 5), dtype=torch.float32) / 25.0

# 3. 利用grouped卷积对RGB三个通道分别做卷积
filtered = F.conv2d(img_tensor, kernel, padding=2, groups=3)

# 4. squeeze去掉batch维度, 并转换为numpy数组
out = filtered.squeeze(0).detach().numpy().transpose(1, 2, 0)

# 5. 将输出转换为0-255范围的uint8类型，并保存图像
out = (out * 255).clip(0, 255).astype(np.uint8)
cv2.imwrite("pytorch_filtered.jpg", cv2.cvtColor(out, cv2.COLOR_RGB2BGR))

