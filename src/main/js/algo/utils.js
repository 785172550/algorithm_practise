const width = 1200;
const height = 600;

const svg = d3.select('#frame')
  .attr('height', height)
  .attr('width', width);

d3.csv('http://127.0.0.1:8887/visualization/population.csv').then(
  (rows) => {
    console.log("start");
    rows.forEach(r => {
      // r.population = +r.population
      r.population = parseInt(r.population)
    });

    var newRows = rows.map(r => {
      return {"x": r.country, "y": r.population}
    });

    drawRect(svg, width, height, newRows);
  }
);


export function sortData(data, desc) {
  data.sort((a, b) => desc ? b - a : a - b)
}

export function drawRect(svg, width, height, data) {
  const margin = {left: 150, right: 150, top: 50, bottom: 50};
  const innerWidth = width - margin.left - margin.right;
  const innerHeight = height - margin.top - margin.bottom;

  const xValue = d => d.x;
  const yValue = d => d.y;

  // 比例尺
  const xScale = d3.scaleBand()
    .domain(data.map(xValue))
    .range([0, innerWidth])
    .padding(0.2);

  const yScale = d3.scaleLinear()
    .domain([0, (d3.max(data, yValue) + 1000)])
    // .range([0, innerHeight]); // 坐标轴方向
    .range([innerHeight, 0]); // 坐标轴方向

  // 坐标轴
  const yaxis = d3.axisLeft(yScale);
  const xaxis = d3.axisBottom(xScale);

  // 整个坐标系
  const g = svg.append('g').attr('transform', `translate(${margin.left},${margin.top})`);
  g.append('g').call(yaxis);
  g.append('g').attr('class', 'axis').call(xaxis).attr('transform', `translate(0,${innerHeight})`);

  g.selectAll('rect').data(data).enter()
    .append('rect')
    .attr('width', xScale.bandwidth())
    .attr('height', d => yScale((d3.max(data, yValue) + 1000) - d.y))
    .attr('x', d => xScale(d.x)) // rect 偏移
    .attr('y', d => yScale(d.y));


  var texts = g.selectAll(".MyText")
    .data(data)
    .enter()
    .append("text")
    .attr("class", "MyText")
    .attr("x", (d, i) => xScale(d.x) + xScale.bandwidth() / 4)
    .attr("y", d => yScale(d.y) - 5)
    .text(d => d.y);
}


