#!/usr/bin/env node
const fs = require("fs");
const path = require("path");

const input = process.argv[2];   // build/module-deps.json
const output = process.argv[3];  // docs/module-deps.md

const data = JSON.parse(fs.readFileSync(input, "utf8"));

// 👉 출력 디렉토리 보장
const outDir = path.dirname(output);
if (!fs.existsSync(outDir)) {
  fs.mkdirSync(outDir, { recursive: true });
}

let lines = [];
lines.push("```mermaid");
lines.push("graph TD");

Object.entries(data).forEach(([from, deps]) => {
  const fromId = from.replace(/:/g, "_").replace(/-/g, "_");

  lines.push(`    ${fromId}["${from}"]`);

  deps.forEach(to => {
    const toId = to.replace(/:/g, "_").replace(/-/g, "_");
    lines.push(`    ${fromId} --> ${toId}`);
  });
});

lines.push("```");

fs.writeFileSync(output, lines.join("\n"));
console.log(`Mermaid graph written to ${output}`);
