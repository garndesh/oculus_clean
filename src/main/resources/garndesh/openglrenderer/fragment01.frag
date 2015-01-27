#version 330 core

uniform sampler2D tex;

in vec2 textureCoords;
out vec4 outColor;

void main() {
	outColor= texture(tex, textureCoords);
}