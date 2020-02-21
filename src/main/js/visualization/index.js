function readFile(path) {
  var xhr = new XMLHttpRequest();
  xhr.open("get", path, false);
  xhr.overrideMimeType("text/html;charset=utf-8");
  xhr.send();
  return xhr.responseText
}

// var csvFile = readFile("http://127.0.0.1:8887/population.csv")
// console.log(csvFile);

const width = 1200;
const height = 600;

const svg = d3.select('.barchart')
  .attr('height', height)
  .attr('width', width);

const render = (data) => {
  const margin = {left: 150, right: 150, top: 50, bottom: 50};
  const innerWidth = width - margin.left - margin.right;
  const innerHeight = height - margin.top - margin.bottom;

  const xValue = d => {
    return d.population
  };
  const yValue = d => {
    return d.country
  };

  const xScale = d3.scaleLinear()
    .domain([0, d3.max(data, xValue)])
    .range([0, innerWidth]);

  const yScale = d3.scaleBand().domain(data.map(yValue))
    .range([0, innerHeight])
    .padding(0.2);

  const yaxis = d3.axisLeft(yScale);
  const xaxis = d3.axisBottom(xScale);

  console.log(yScale.domain());

  const g = svg.append('g')
    .attr('transform', `translate(${margin.left},${margin.top})`)

  g.append('g').call(yaxis);
  g.append('g').call(xaxis)
    .attr('transform', `translate(0,${innerHeight})`);

  g.selectAll('rect').data(data).enter().append('rect')
    .attr('width', d => {
      return xScale(d.population)
    })
    .attr('height', yScale.bandwidth())
    .attr('y', d => yScale(d.country))
};

d3.csv('http://127.0.0.1:8887/population.csv').then(
  (row) => {
    row.forEach(r => {
      // r.population = +r.population
      r.population = parseInt(r.population)
    });
    console.log(row);
    render(row);
  }
);